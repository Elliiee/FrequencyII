package top20;

import top20.*;
import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses_20 {
    /*
    Given a string containing just the characters '(', ')', '{', '}', '[' and ']',
    determine if the input string is valid.
    An input string is valid if:
    1. open brackets must be closed by the same type of brackets
    2. open brackets must be closed in the correct order
    Note that an empty string is also considered valid.
     */

    /*
    Intuition:
    1. we process the expression one bracket at a time starting from the left.
    2. suppose we encounter an opening bracket '('. It may or may not be an invalid
    expression because there can be a matching ending bracket somewhere in the remaining
    part of the expression. Here, we simply increment the counter keeping track of left
    parenthesis till now. left += 1
    3. If we encounter a closing bracket, this has two meanings.
        3.1 there is no matching opening bracket for this closing bracket, left == 0
            Invalid expression
        3.2 there are some unmatched opening bracket available for this, left > 0
            decrement left thus reducing the number of unmached left parenthesis available
    4. continue the process until all parenthesis have been processed.
    5. if int the end we still have unmatched left parenthesis available, it is invalid.
    But the above is not the correct solution for this questions.
    Because there are 3 different type of expressions, (), {}, []
     */

    /*
    Approach 1: Stacks
    An interesting property about a valid parenthesis expression is that a sub-expression
    of a valid expression should also be a valid expression.
    This leads to a sort of a recursive structure to the problem.
    What if whenever we encounter a matching pair of parenthesis in the expression,
    we simply remove it from the expression?
    The stack data structure can come in handy.
    We can't really process this from the inside out because we don't know the overall sequence/ structure.
    Algorithm:
    1. initialize a stack S.
    2. process each bracket of the expression one at a time.
    3. If we encounter an open bracket, we simply push it onto the stack.
    4. if we encounter a closing bracket, then we check the element on top of the stack.
        if the element at the top of the stack is an opening bracket of the same type.
        then we pop it off the stack and continue processing.
        Else, this is an invalid expression.
    5. in the end, if we are left with a stack still having elements--invalid!
     */

    /*
    Time complexity: O(N) because we simply traverse the given string one character
    at a time and push and pop operations on a stack take O(1) time.
    Space complexity: O(N) as we push all opening brackets onto the stack and in the
    worst case, we will end up pushing all the brackets onto the stack.
     */

    private HashMap<Character, Character> mappings;

    public ValidParentheses_20(){
        this.mappings = new HashMap<Character, Character>();
        this.mappings.put(')', '(');
        this.mappings.put('}', '{');
        this.mappings.put(']', '[');
    }

    public boolean isValid(String s){
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            // if the current character is a closing bracket.
            if (this.mappings.containsKey(c)){
                // get the top element of the stack. set a dummy value # if it's empty.
                char topElement = stack.empty() ? '#' : stack.pop();

                // if the mapping does not match--invalid
                if (topElement != this.mappings.get(c)){
                    return false;
                }
            }
            else {
                // if it's an open bracket, push it to the stack.
                stack.push(c);
            }
        }

        // stack still contains elements--invalid
        return stack.isEmpty();
    }
}
