package codesage.backend.Dto;

public record CitationDto(
        String filePath, Integer startLine, Integer endLine, String language) {
}
