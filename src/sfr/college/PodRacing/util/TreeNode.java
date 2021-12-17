/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sfr.college.PodRacing.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Sami
 */
public class TreeNode<T> implements Iterable<TreeNode<T>> {

    private T data;
    private TreeNode<T> parent;
    private List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<TreeNode<T>>();
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public TreeNode<T> addChild(T child) {
        TreeNode<T> childNode = new TreeNode<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getChild(int index) {
        if (index >= this.children.size()) return null;
        return this.children.get(index);
    }

    @Override
    public Iterator<TreeNode<T>> iterator() {
        return null;
    }

    public void kill() {
        this.data = null;
    }

    public TreeNode getNode(T data) {
        if (this.data.equals(data)) return this;
        return null;
    }

}