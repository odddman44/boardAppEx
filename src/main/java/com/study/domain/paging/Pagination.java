package com.study.domain.paging;

import lombok.Getter;

@Getter
public class Pagination {

    private int totalRecordCount;       // 전체 데이터 수
    private int totalPageCount;         // 전체 페이지 수
    private int startPage;              // 첫 페이지 번호
    private int endPage;                // 끝 페이지 번호
    private int limitStart;             // LIMIT 시작 위치
    private boolean existPrevPage;      // 이전 페이지 존재 여부
    private boolean existNextPage;      // 다음 페이지 존재 여부

    public Pagination(int totalRecordCount, CommomParams params) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            this.caculation(params);
        }
    }

    private void caculation(CommomParams params) {

        // 전체 페이지 수 계산
        totalPageCount = ((totalRecordCount - 1) / params.getRecordPerPage()) + 1;

        // 현재 페이지 번호가 전체 페이지 수 보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
        if (params.getPage() > totalPageCount) {
            params.setPage(totalPageCount);
        }

        // 첫 페이지 번호 계산
        startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;

        //TODO : 끝 페이지 번호 계산 ~~~
    }
}
