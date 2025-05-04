package routiner.core.domain.usecase.category

import routiner.core.model.Category
import routiner.core.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesFlowUseCase @Inject constructor(private val repository: CategoryRepository) {

    operator fun invoke(): Flow<List<Category>> {
        return repository.getCategoriesFlow()
    }

}
