package app.tp136.dto;

public record ProductSearchParamsDto(
        String[] name,
        String[] description,
        String[] country,
        String[] year,
        String[] material) {
}
