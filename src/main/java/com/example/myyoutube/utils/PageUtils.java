package com.example.myyoutube.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.springframework.data.domain.Sort.by;

public class PageUtils {

    public static Pageable getPageable(Integer pageNumber, Integer pageSize, String sortDirection, String... sortFields) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        return getPageable(pageNumber, pageSize, direction, sortFields);
    }

    public static Pageable getPageable(Integer pageNumber, Integer pageSize, Sort.Direction direction, String... sortFields) {
        if (pageNumber == null) {
            pageNumber = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        Sort sort = by(direction, sortFields);
        return PageRequest.of(pageNumber, pageSize, sort);
    }


}
