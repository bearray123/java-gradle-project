/*
 * Copyright (C) 2022 Baidu, Inc. All Rights Reserved.
 */
package xyz.rh.testj.algorithm.structure;

public final class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int data) {
        this.val = data;
    }

    public ListNode(int data, ListNode next) {
        this.val = data;
        this.next = next;
    }


    public ListNode buildNext(int nextData) {
        ListNode node = new ListNode(nextData);
        this.next = node;
        return node;
    }

    public static ListNode buildListNodeFromArray(int[] array) {
        ListNode head = null, p = null;
        for (int i=0; i< array.length ; i++) {
            ListNode curr = new ListNode(array[i]);
            if(head == null) {
                head = curr;
                p = curr;
            } else {
                p.next = curr;
                p = p.next;
            }
        }
        return head;
    }

}