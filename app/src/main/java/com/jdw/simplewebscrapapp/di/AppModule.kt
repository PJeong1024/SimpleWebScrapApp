package com.jdw.simplewebscrapapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    val generativeModel = GenerativeModel(
//        modelName = "gemini-pro",
//        apiKey = BuildConfig.GEMINI_API_KEY
//    )

//    @Singleton
//    @Provides
//    fun provideRecipeDao(recipeDatabase: RecipeDatabase): RecipeDao = recipeDatabase.recipeDao()
//
//    @Singleton
//    @Provides
//    fun provideRecipeDatabase(@ApplicationContext context: Context): RecipeDatabase =
//        Room.databaseBuilder(context, RecipeDatabase::class.java, "recipe_db")
//            .fallbackToDestructiveMigration().build()
//
//    @Provides
//    fun provideRecipeRepository(recipeDao: RecipeDao): RecipeRepository {
//        return RecipeRepository(recipeDao)
//    }
//
//
//    @Singleton
//    @Provides
//    fun provideGeminiApiGenerativeModel(): GenerativeModel {
//        return GenerativeModel(
//            modelName = "gemini-pro",
//            apiKey = BuildConfig.GEMINI_API_KEY
//        )
//    }

//    @Singleton
//    @Provides
//    fun provideFirebaseBookRepository(): FirebaseBookRepository {
//        return FirebaseBookRepository(
//            queryBook = FirebaseFirestore.getInstance().collection("books")
//        )
//    }
//
//    @Singleton
//    @Provides
//    fun provideBookRepository(api: BooksApi): BookRepository {
//        return BookRepository(api)
//    }
//
//    @Singleton
//    @Provides
//    fun provideBookApi(): BooksApi {
//        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build().create(BooksApi::class.java)
//    }

}