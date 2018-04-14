package com.diff.api.resource;

import com.diff.api.resource.enums.Result;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;


@XmlRootElement
public class DiffOutput implements Serializable {

    private Integer id;
    private Result result;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Diff> diffList;

    public DiffOutput(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @XmlElement
    public void setId(Integer id) {
        this.id = id;
    }

    public Result getResult() {
        return result;
    }

    @XmlElement
    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return String.format("id='%s' DiffResult='%s' Diffs=[%s]", id, result, diffList);
    }

    public List<Diff> getDiffList() {
        return diffList;
    }

    @XmlElement
    public void setDiffList(List<Diff> diffList) {
        this.diffList = diffList;
    }
}
