package com.tenacity.free.project.manager.po;

public class TenacityFreeProjectManagerTestHistoryPoWithBLOBs extends TenacityFreeProjectManagerTestHistoryPo {
    private String requestHeaders;

    private String queryParams;

    public String getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(String requestHeaders) {
        this.requestHeaders = requestHeaders == null ? null : requestHeaders.trim();
    }

    public String getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(String queryParams) {
        this.queryParams = queryParams == null ? null : queryParams.trim();
    }
}