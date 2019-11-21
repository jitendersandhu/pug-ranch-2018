package org.millr.core.models;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

@Model(
    adaptables = { SlingHttpServletRequest.class },
    resourceType = "pugranch/components/content/tabControl",
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
@Exporter(name = "jackson", extensions = "json")
public class TabControl {
    @ValueMapValue
    private String title;

    @SlingObject
    private SlingHttpServletRequest request;

    @SlingObject
    private ResourceResolver resolver;

    @SlingObject
    private Resource resource;

    public String getTitle() {
        return title;
    }

    public String getSort() {
        return request.getParameter("sort");
    }

    public List<String> getChildren() {

        List<String> children = new ArrayList<String>();

        ValueMap properties = resource.adaptTo(ValueMap.class);
        if (properties.containsKey("tabItems")) {
            String[] arr = properties.get("tabItems", new String[0]);

            if (arr != null && arr.length > 0) {
                for (int i = 0; i < arr.length; i++) {
                    children.add(arr[i]);
                }
            }
            return children;
        }
        return null;
    }


}
