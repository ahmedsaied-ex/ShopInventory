package com.example.shopstock.di

import android.content.Context
import androidx.room.Room
import com.example.shopstock.data.local.AppDatabase
import com.example.shopstock.data.local.ItemDao
import com.example.shopstock.data.local.ItemLocalDataSource
import com.example.shopstock.data.repository.ItemRepositoryImpl
import com.example.shopstock.domain.repository.ItemRepository
import com.example.shopstock.domain.useCases.*
import com.example.shopstock.helpers.Const.ITEMs_DB
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

    // -------- Use Cases --------
    @Provides
    @Singleton
    fun provideGetItemsUseCase(repository: ItemRepository) = GetItemsUseCase(repository)

    @Provides
    @Singleton
    fun provideMergeSortAscUseCase() = MergeSortAscUseCase()

    @Provides
    @Singleton
    fun provideQuickSortDescUseCase() = QuickSortDescUseCase()

    @Provides
    @Singleton
    fun provideBinarySearchByNameUseCase() = BinarySearchByNameUseCase()

    @Provides
    @Singleton
    fun provideSortItemsByNameUseCase() = SortItemsByNameUseCase()

    // -------- Bundle all use cases --------
    @Provides
    @Singleton
    fun provideUseCases(
        getItemsUseCase: GetItemsUseCase,
        mergeSortAscUseCase: MergeSortAscUseCase,
        quickSortDescUseCase: QuickSortDescUseCase,
        binarySearchByNameUseCase: BinarySearchByNameUseCase,
        sortItemsByNameUseCase: SortItemsByNameUseCase
    ): UseCases = UseCases(
        getItems = getItemsUseCase,
        mergeSortAsc = mergeSortAscUseCase,
        quickSortDesc = quickSortDescUseCase,
        binarySearchByName = binarySearchByNameUseCase,
        sortAsc = sortItemsByNameUseCase
    )
}
