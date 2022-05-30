package libraries.pojoClasses;

import java.util.List;

public class Users {
    private Integer page;
    private Integer per_page;
    private Integer total;
    private Integer total_pages;
    private List<User> data = null;
    private Support support;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getper_page() {
        return per_page;
    }

    public void setper_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer gettotal_pages() {
        return total_pages;
    }

    public void settotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    public Support getSupport() {
        return support;
    }

    public void setSupport(Support support) {
        this.support = support;
    }

}