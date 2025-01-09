package com.study.utils.paging;

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

    public Pagination(int totalRecordCount, CommonParams params) {
        if (totalRecordCount > 0) {
            this.totalRecordCount = totalRecordCount;
            this.calculation(params);
        }
    }

    private void calculation(CommonParams params) {

        // 전체 페이지 수 계산
        totalPageCount = ((totalRecordCount - 1) / params.getRecordPerPage()) + 1;

        // 현재 페이지 번호가 전체 페이지 수 보다 큰 경우, 현재 페이지 번호에 전체 페이지 수 저장
        // ex) 전체 페이지가 5개인데, 사용자가 6페이지 요청시 -> 5페이지로 수정
        if (params.getPage() > totalPageCount) {
            params.setPage(totalPageCount);
        }

        // 첫 페이지 번호 계산
        startPage = ((params.getPage() - 1) / params.getPageSize()) * params.getPageSize() + 1;

        // 끝 페이지 번호 계산
        endPage = startPage + params.getPageSize() - 1;

        // 끝 페이지가 전체 페이지 수보다 큰 경우, 끝 페이지 전체 페이지 수 저장
        if (endPage > totalPageCount) {
            endPage = totalPageCount;
        }

        // LIMIT 시작 위치 계산
        limitStart = (params.getPage() -1) * params.getRecordPerPage();

        // 이전 페이지 존재 여부 확인
        existPrevPage = startPage != 1;

        // 다음 페이지 존재 여부 확인
        existNextPage = (endPage * params.getRecordPerPage()) < totalRecordCount;
    }
}
