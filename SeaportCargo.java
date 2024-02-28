import java.util.Stack;
import java.util.ArrayList;

public class SeaportCargo {
    public static void main(String[] args) {
        String input = "ASIUGDOQIHBSOIJANPSOMDPIUABSODPB"; 
        ArrayList<LetterStack> requiredStacksForInput = getStacksNeeded(input);
        printStacks(requiredStacksForInput, input);

    }
    private static void printStacks(ArrayList<LetterStack> stacks, String input){
        System.out.print("The input \"" + input + "\" requires at least: " + stacks.size() + " stacks.\nHere they are: \n\n");
        String s = "";
        for(LetterStack st : stacks){
            s += st.toString() + "\n";
        }
        System.out.println(s);
    }
    private static LetterStack getBestStack(ArrayList<LetterStack> stacks, char next){
        int bestDiff = -30;
        LetterStack bestStack = null;
        for(LetterStack ls : stacks){
            int diff = ls.getCompareTo(next);
            if(diff >= bestDiff && diff <= 0){
                bestDiff = diff;
                bestStack = ls;
            }
        }
        return bestStack;
    }
    private static ArrayList<LetterStack> getStacksNeeded(String input){
        ArrayList<LetterStack> stacks = new ArrayList<>();

        for(int i = 0; i < input.length(); i++){
            char next = input.charAt(i);
            LetterStack bestStack = getBestStack(stacks, next);
            if(bestStack != null){
                bestStack.addLetter(next);
            }
            else{
                LetterStack s = new LetterStack(next); 
                stacks.add(s);
            }
        }
        return stacks;
    }
}
class LetterStack {
    private Stack<Character> stack = new Stack<>();

    public LetterStack(char c)
    {
        stack.add(c);
    }

    public boolean canAddLetter(char c){
        return (c + "").compareTo(stack.peek() + "") <= 0;
    }
    public int getCompareTo(char c){
        return (c + "").compareTo(stack.peek() + "");
    }
    public void addLetter(char c){
        stack.add(c);
    }
    @Override
    public String toString(){
        Character[] copy = new Character[stack.size()];
        stack.copyInto(copy);
        String s = "";
        s += "{ ";
        for(int i = 0; i < copy.length; i++){
            s += copy[i] + " ";
        }
        s += "}";
        return s;
    }
}
