package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.geekbrains.persist.entity.User;
import ru.geekbrains.persist.repo.PageRepository;

import java.util.List;

@Service
public class PageUserService {

    PageRepository pageRepository;

    @Autowired
    public void setPageRepository(PageRepository pageRepository) {
        this.pageRepository = pageRepository;
    }

    public List<User> findAllWithPaging(Integer pageNumber, Integer pageSize){
        return pageRepository.findAllBy(PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.ASC, "age")));
    }
}
