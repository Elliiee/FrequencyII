package top20;

public class AddTwoNumbers_2 {
    /*
    2. Add Two Numbers
    Medium
    You are given two non-empty linked lists representing two non-negative integers. The digits are
    stored in reverse order and each of their nodes contain a single digit. Add the two numbers and
    return it as a linked list.
    You may assume the two numbers do not contain any leading zero, except the number 0 itself.
     */

    /*
    Approach 1: Elementary Math
    Intuition:
    Keep track of the carry using a variable.
    Simulate digits-by-digits sum starting from the head of the list, which contains the least
    significant digit.
    Algorithm:
    Just like how you would sum two numbers on a piece of paper, we begin by summing the least
    significant digits, which is the head of l1 and l2.
    In case the summing overflow (>10), we create a carry to bring the overflow up.
    Note that we use a dummy head to simplify the code.
    Without a dummy head, you would have to write extra conditional statements to initialize the
    head's value.
    1. initialize current node to dummy head of the returning list.
    2. initialize carry to 0
    3. initialize p and q to head of l1 and l2 respectively.
    4. Loop through lists l1 and l2 until you reach both ends.
        set x to node p's value. if p has reached the end of l1, set to 0
        set y to node q's value. if q has reached the end of l2, set to 0
        set sum = x + y + carry
        update carry = sum/10
        create a new node with the digit value of (sum mod 10) and set it to current node's next,
        then advance current node to next
        advance both p and q
    5. check if carry = 1, if so append a new node with digit 1 to the returning list.
    6. return dummy head's next node
     */
    public ListNode addTwoNumber(ListNode l1, ListNode l2){
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null){
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0){
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }
}
