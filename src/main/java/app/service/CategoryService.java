package app.service;

import app.entity.Category;
import app.repo.CategoryRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class CategoryService {
  private final CategoryRepo categoryRepo;

  public CategoryService(CategoryRepo categoryRepo) {
    this.categoryRepo = categoryRepo;
  }

  public List<Category> findAll() {
    return categoryRepo.findAll();
  }

}
