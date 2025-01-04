package app.tp136.dto;

public record ProductSearchParamsDto(
        String[] name,
        String[] partFromDescription,
        String[] country,
        String[] year,
        String[] material) {
}
