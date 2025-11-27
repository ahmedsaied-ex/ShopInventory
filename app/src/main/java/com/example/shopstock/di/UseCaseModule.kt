import com.example.shopstock.domain.useCases.BinarySearchByNameUseCase
import com.example.shopstock.domain.useCases.GetItemsUseCase
import com.example.shopstock.domain.useCases.MergeSortAscUseCase
import com.example.shopstock.domain.useCases.QuickSortDescUseCase
import com.example.shopstock.domain.useCases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object UseCaseModule {
//
//    @Provides
//    @Singleton
//    fun provideUseCases(
//        getItemsUseCase: GetItemsUseCase,
//        mergeSortAscUseCase: MergeSortAscUseCase,
//        quickSortDescUseCase: QuickSortDescUseCase,
//        binarySearchByNameUseCase: BinarySearchByNameUseCase
//    ): UseCases = UseCases(
//        getItems = getItemsUseCase,
//        mergeSortAsc = mergeSortAscUseCase,
//        quickSortDesc = quickSortDescUseCase,
//        binarySearchByName = binarySearchByNameUseCase
//    )
//}
