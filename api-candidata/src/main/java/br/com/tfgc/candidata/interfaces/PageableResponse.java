package br.com.tfgc.candidata.interfaces;

import java.util.Collection;
import org.springframework.http.ResponseEntity;

public class PageableResponse<T>{
    private final Collection<T> data;

    private Long contentRange;
    private Integer acceptRange;
    private Integer contentPages;

    public PageableResponse(Collection<T> data) {
        this.data = data;
    }

    public static <T> PageableResponse<T> create(Collection<T> data){
        return new PageableResponse<>(data);
    }

    public PageableResponse<T> acceptRange(Integer acceptRange){
        this.acceptRange = acceptRange;
        return this;
    }

    public PageableResponse<T> contentRange(Long contentRange){
        this.contentRange = contentRange;
        return this;
    }

    public PageableResponse<T> contentPages(Integer contentPages){
        this.contentPages = contentPages;
        return this;
    }

    public ResponseEntity<Collection<T>> build(){
        return ResponseEntity.ok()
            .header("Accept-Range", String.valueOf(this.acceptRange))
            .header("Content-Range", String.valueOf(this.contentRange))
            .header("Content-Pages", String.valueOf(this.contentPages))
            .body(this.data);
    }
}
