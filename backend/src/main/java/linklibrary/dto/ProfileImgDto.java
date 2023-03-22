package linklibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class ProfileImgDto {
    private MultipartFile profileImg;
}
