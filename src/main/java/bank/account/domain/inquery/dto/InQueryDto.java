package bank.account.domain.inquery.dto;

import bank.account.domain.inquery.model.InQuery;
import bank.account.domain.user.model.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

public class InQueryDto {

    /**
     * Request
     */
    public record InQueryWriteRequest(
            @NotNull
            String title,
            @NotNull
            String content
    ) {
        public InQuery toEntity(User user) {
            return InQuery.builder()
                    .title(title)
                    .content(content)
                    .user(user)
                    .build();
        }
    }

    public record InQueryAnswerRequest(
            @Positive
            @NotNull
            Long id,
            @NotNull
            String answer
    ) {

    }

    /**
     * Response
     */
    @Builder
    public record InQueryDetailResponse(
            String title,
            String content,
            String answer,
            String userName
    ) {
        public static InQueryDetailResponse of(InQuery inQuery) {
            return InQueryDetailResponse.builder()
                    .title(inQuery.getTitle())
                    .content(inQuery.getContent())
                    .answer(inQuery.getAnswer())
                    .userName(inQuery.getUser().getName())
                    .build();
        }
    }

    @Builder
    public record InQueryListResponse(
            Long id,
            String title,
            String content,
            String answer,
            boolean isAnswer
    ) {
        public static InQueryListResponse of(InQuery inQuery) {
            return InQueryListResponse.builder()
                    .id(inQuery.getId())
                    .title(inQuery.getTitle())
                    .content(inQuery.getContent())
                    .answer(inQuery.getAnswer())
                    .isAnswer(inQuery.getIsAnswer())
                    .build();
        }
    }

    @Builder
    public record AdminInQueryListResponse(
            Long id,
            String title,
            String userName,
            String content,
            String answer,
            boolean isAnswer
    ) {
        public static AdminInQueryListResponse of(InQuery inQuery) {
            return AdminInQueryListResponse.builder()
                    .id(inQuery.getId())
                    .title(inQuery.getTitle())
                    .userName(inQuery.getUser().getName())
                    .content(inQuery.getContent())
                    .answer(inQuery.getAnswer())
                    .isAnswer(inQuery.getIsAnswer())
                    .build();
        }
    }
}

