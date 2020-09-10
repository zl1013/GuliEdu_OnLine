package com.zzl.order.client;

import com.zzl.commonutils.ordervo.CourseOrder;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/10 13:02
 * Version 1.0
 */
@Component
public class CourseClientFallback implements CourseClient {

    @Override
    public CourseOrder getCourseInfo(String courseId) {
        return null;
    }
}
