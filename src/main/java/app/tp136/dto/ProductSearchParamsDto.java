package app.tp136.dto;

public record ProductSearchParamsDto(
        String[] nameUa,
        String[] nameEng,
        String[] descriptionUa,
        String[] descriptionEng,
        String[] countryUa,
        String[] countryEng,
        String[] year,
        String[] materialUa,
        String[] materialEng) {
}
