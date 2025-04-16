package cn.cat.trigger.http;

import cn.cat.api.dto.TagRequestDTO;
import cn.cat.api.response.Response;
import cn.cat.domain.tag.service.ITagService;
import cn.cat.types.enums.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController()
@CrossOrigin("*")
@RequestMapping("/api/v1/gbm/tag/")
public class TagController {

    @Resource
    private ITagService tagService;

    @RequestMapping(value = "add_to_tag", method = RequestMethod.POST)
    public Response<String> addToTag(@RequestBody TagRequestDTO tagRequestDTO) {
        try {
            String tagId = tagRequestDTO.getTagId();
            String userId = tagRequestDTO.getUserId();
            if (StringUtils.isEmpty(tagId) || StringUtils.isEmpty(userId)) {
                return Response.<String>builder()
                        .code(ResponseCode.ILLEGAL_PARAMETER.getCode())
                        .info(ResponseCode.ILLEGAL_PARAMETER.getInfo())
                        .build();
            }
            tagService.addToTag(tagId, userId);
            return Response.<String>builder()
                    .code(ResponseCode.SUCCESS.getCode())
                    .info(ResponseCode.SUCCESS.getInfo())
                    .data("success")
                    .build();
        } catch (Exception e) {
            return Response.<String>builder()
                    .code(ResponseCode.UN_ERROR.getCode())
                    .info(ResponseCode.UN_ERROR.getInfo())
                    .build();
        }
    }

}
