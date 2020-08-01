package us.siriusteam.springboot.datajpa.app.util.paginator;


import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador de la paginacion
 * @param <T> el tipo de objeto a paginar
 */
public class PageRender<T> {
    private String url;
    private Page<T> page;
    private int totalPage;
    private int numeroElementosPorPagina;
    private int paginaActual;
    private List<PageItem> paginas;

    public PageRender(String url, Page<T> page) {
        this.url = url;
        this.page = page;
        this.paginas = new ArrayList<>();

        numeroElementosPorPagina = page.getSize();
        totalPage = page.getTotalPages();
        paginaActual = page.getNumber() + 1;

        int desde, hasta;
        if (totalPage <= numeroElementosPorPagina) {
            desde = 1;
            hasta = totalPage;
        } else {
            if (paginaActual <= numeroElementosPorPagina/2){
                desde = 1;
                hasta = numeroElementosPorPagina;
            } else if(paginaActual >= (totalPage - numeroElementosPorPagina)) {
                desde = (totalPage - numeroElementosPorPagina) + 1;
                hasta = numeroElementosPorPagina;
            } else {
                desde = paginaActual - (numeroElementosPorPagina/2);
                hasta = numeroElementosPorPagina;
            }
        }

        for (int i = 0; i < hasta; i++) {
            paginas.add(new PageItem(desde + i, paginaActual == desde + i));
        }
    }

    public String getUrl() {
        return url;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getPaginaActual() {
        return paginaActual;
    }

    public List<PageItem> getPaginas() {
        return paginas;
    }

    public boolean isFirst() {
        return page.isFirst();
    }

    public boolean isLast() {
        return page.isLast();
    }

    public boolean isHasNext() {
        return page.hasNext();
    }

    public boolean isHasPrevious() {
        return page.hasPrevious();
    }
}
