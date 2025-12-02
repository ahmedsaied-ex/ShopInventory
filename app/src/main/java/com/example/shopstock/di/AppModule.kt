package com.example.shopstock.di

import android.content.Context
import androidx.room.Room
import com.example.shopstock.data.local.AppDatabase
import com.example.shopstock.data.local.ItemDao
import com.example.shopstock.data.local.ItemLocalDataSource
import com.example.shopstock.data.repository.ItemRepositoryImpl
import com.example.shopstock.domain.repository.ItemRepository
import com.example.shopstock.domain.useCases.implementation.BinarySearchByNameUseCaseImpl
import com.example.shopstock.domain.useCases.implementation.GetItemsUseCaseImpl
import com.example.shopstock.domain.useCases.interfaces.IBinarySearchByNameUseCase
import com.example.shopstock.domain.useCases.interfaces.IGetItemsUseCase
import com.example.shopstock.domain.useCases.interfaces.IMergeSortAscUseCase
import com.example.shopstock.domain.useCases.interfaces.IQuickSortDescUseCase
import com.example.shopstock.domain.useCases.interfaces.ISortItemsByNameUseCase
import com.example.shopstock.domain.useCases.implementation.MergeSortAscUseCaseImpl
import com.example.shopstock.domain.useCases.implementation.QuickSortDescUseCaseImpl
import com.example.shopstock.domain.useCases.implementation.SortItemsByNameUseCaseImpl
import com.example.shopstock.helpers.Const.ITEMs_DB
import com.example.shopstock.domain.useCases.UseCases

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // -------- Room --------
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            ITEMs_DB
        ).build()

    @Provides
    @Singleton
    fun provideItemDao(db: AppDatabase): ItemDao = db.itemDao()


    // -------- Data Source --------
    @Provides
    @Singleton
    fun provideLocalDataSource(dao: ItemDao): ItemLocalDataSource =
        ItemLocalDataSource(dao)


    // -------- Repository --------
    @Provides
    @Singleton
    fun provideItemRepository(localDataSource: ItemLocalDataSource): ItemRepository =
        ItemRepositoryImpl(localDataSource)


    // -------- Use Cases Implementations --------

    @Provides
    @Singleton
    fun provideMergeSortAscUseCase(): IMergeSortAscUseCase =
        MergeSortAscUseCaseImpl()

    @Provides
    @Singleton
    fun provideQuickSortDescUseCase(): IQuickSortDescUseCase =
        QuickSortDescUseCaseImpl()

    @Provides
    @Singleton
    fun provideBinarySearchByNameUseCase(): IBinarySearchByNameUseCase =
        BinarySearchByNameUseCaseImpl()

    @Provides
    @Singleton
    fun provideSortItemsByNameUseCase(): ISortItemsByNameUseCase =
        SortItemsByNameUseCaseImpl()

    @Provides
    @Singleton
    fun provideGetItemsUseCase(repository: ItemRepository): IGetItemsUseCase =
        GetItemsUseCaseImpl(repository)


    // -------- Bundle UseCases --------
    @Provides
    @Singleton
    fun provideUseCases(
        getItemsUseCase: IGetItemsUseCase,
        mergeSortAscUseCase: IMergeSortAscUseCase,
        quickSortDescUseCase: IQuickSortDescUseCase,
        binarySearchByNameUseCase: IBinarySearchByNameUseCase,
        sortItemsByNameUseCase: ISortItemsByNameUseCase
    ): UseCases = UseCases(
        getItems = getItemsUseCase,
        mergeSortAsc = mergeSortAscUseCase,
        quickSortDesc = quickSortDescUseCase,
        binarySearchByName = binarySearchByNameUseCase,
        sortAsc = sortItemsByNameUseCase
    )
}
