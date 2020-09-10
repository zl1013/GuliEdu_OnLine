package com.zzl.order.client;

import com.zzl.commonutils.ordervo.CourseOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Description:
 * Created by 乍暖还寒 on 2020/9/10 13:00
 * Version 1.0
 */
@Component
@FeignClient(value = "service-edu",fallback = CourseClientFallback.class)
public interface CourseClient {
    @GetMapping("/eduservice/coursefront/getCourseInfoOrder/{courseId}")
    public CourseOrder getCourseInfo(@PathVariable String courseId);

}
