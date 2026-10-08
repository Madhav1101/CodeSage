package codesage.backend.Service.ai;

import java.util.List;

import codesage.backend.Dto.CitationDto;

public record RetrievedContext(List<CitationDto> citations, String contextText) {
}
