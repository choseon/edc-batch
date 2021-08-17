package external.data.collection.batch.jobs.biz.biz001m.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultObjectVO {

    List<Biz001mVO> jsonArray = new ArrayList<>();
}
