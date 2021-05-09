package com.example.finalProject.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalProject.api.APIService
import com.example.finalProject.models.*
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokemonDetailsViewModel: ViewModel() {
    private val details = MutableLiveData<PokemonDetail>()
    private var service: APIService

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(APIService::class.java)
    }

    private fun getPokemon(name: String): Observable<Pokemon> {
        return Observable.create { emitter ->
            service.getPokemonByName(name)
                .enqueue(object : Callback<Pokemon> {
                    override fun onResponse(
                        call: Call<Pokemon>,
                        response: Response<Pokemon>
                    ) {
                        val body = response.body()

                        if (body != null) {
                            emitter.onNext(body)
                            emitter.onComplete()
                        } else {
                            emitter.onError(Throwable("not found"))
                        }
                    }

                    override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                        emitter.onError(t)
                    }

                })
        }
    }

    private fun getPokemonSpecies(url: String): Observable<PokemonSpecies> {
        return Observable.create { emitter ->
            service.getPokemonSpeciesByUrl(url)
                .enqueue(object : Callback<PokemonSpecies> {
                    override fun onResponse(
                        call: Call<PokemonSpecies>,
                        response: Response<PokemonSpecies>
                    ) {
                        val body = response.body()

                        if (body != null) {
                            emitter.onNext(body)
                            emitter.onComplete()
                        } else {
                            emitter.onError(Throwable("not found"))
                        }
                    }

                    override fun onFailure(call: Call<PokemonSpecies>, t: Throwable) {
                        emitter.onError(t)
                    }
                })
        }
    }

    private fun getEvolution(url: String): Observable<Evolution> {
        return Observable.create { emitter ->
            service.getEvolutionByUrl(url)
                .enqueue(object : Callback<Evolution> {
                    override fun onResponse(
                        call: Call<Evolution>,
                        response: Response<Evolution>
                    ) {
                        val body = response.body()

                        if (body != null) {
                            emitter.onNext(body)
                            emitter.onComplete()
                        } else {
                            emitter.onError(Throwable("not found"))
                        }
                    }

                    override fun onFailure(call: Call<Evolution>, t: Throwable) {
                        emitter.onError(t)
                    }
                })
        }
    }



    fun makeAPIRequest(name: String): Observable<PokemonDetail> {
        return getPokemon(name)
            .concatMap { pokemon ->
                getPokemonSpecies(pokemon.species.url)
                    .concatMap { specie ->
                        getEvolution(specie.evolution_chain.url)
                            .map { evolution ->
                                val pokemonNames = mutableListOf<String>()
                                pokemonNames.add(evolution.chain.species.name)

                                fun recursiveFunction(evolvesTo: List<Evolves>) {
                                    if (evolvesTo.isEmpty()) return

                                    for (evo in evolvesTo) {
                                        pokemonNames.add(evo.species.name)
                                        recursiveFunction(evo.evolves_to)
                                    }
                                }

                                recursiveFunction(evolution.chain.evolves_to)
                                Observable
                                    .fromIterable(pokemonNames)
                                    .concatMap {
                                        if (pokemon.name == it) Observable.just(pokemon) else getPokemon(it)
                                    }
                                    .toList()
                                    .map{
                                        PokemonDetail(pokemon, specie, it)
                                    }
                            }
                    }
            }
            .concatMap {
                Observable.fromSingle(it)
            }
            .concatMap {
                details.postValue(it)
                Observable.just(it)
            }
    }

    fun getPokemonDetail(): MutableLiveData<PokemonDetail> {
        return details
    }
}