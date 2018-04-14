package com.diff.api.comparator;

import com.diff.api.resource.Diff;
import com.diff.api.resource.DiffOutput;
import com.diff.api.resource.ValuesEntry;
import com.diff.api.resource.enums.Result;

import java.util.LinkedList;
import java.util.List;

public class Comparator {

    public DiffOutput compare(ValuesEntry reg) {
        DiffOutput diffOutput = null;

        if (reg.getLeft() != null && reg.getRight() != null) {
            diffOutput = new DiffOutput(reg.getID());
            if (reg.getLeft().equals(reg.getRight())) {
                diffOutput.setResult(Result.EQUAL);
            } else if (reg.getLeft().length() != reg.getRight().length()) {
                diffOutput.setResult(Result.DIFFERENT_SIZE);
            } else {
                diffOutput.setResult(Result.DIFFERENT);
                diffOutput.setDiffList(processDiff(reg.getLeft(), reg.getRight()));
            }
        }
        return diffOutput;
    }


    public List<Diff> processDiff(String left, String right) {
        List<Diff> diffList = new LinkedList<>();

        int length = 0;
        int offset = -1;
        for (int i = 0; i <= left.length(); i++) {
            if (i < left.length()
                    && left.charAt(i) != right.charAt(i)) {
                length++;
                if (offset < 0) {
                    offset = i;
                }
            } else if (offset != -1) {
                diffList.add(new Diff(offset, length));
                length = 0;
                offset = -1;
            }
        }
        return diffList;
    }
}
