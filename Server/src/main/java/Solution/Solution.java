package Solution;

public class Solution implements Result {
    private final String str;
    public Solution(String str) {
        if(str == null || str.equals("")){
            throw new NullPointerException();
        }
        this.str = str;
    }
    @Override
    public String getResult() {
        int lowCounter = 0;
        int highCounter = 0;
        char[] chars = str.toCharArray();
        int length = chars.length;
        for(char c : chars){
            if(Character.isLowerCase(c)){
                lowCounter++;
            }
            if(Character.isUpperCase(c)){
                highCounter++;
            }
        }
        return new StringBuilder("LowerCase: ")
                .append(String.format("%.2f",(double)lowCounter/length*100))
                .append("% | UpperCase: ")
                .append(String.format("%.2f",(double)highCounter/length*100))
                .append("% | Upper&Lower: ")
                .append(String.format("%.2f",(double)(lowCounter+highCounter)/length*100))
                .append("% | Symbols - ")
                .append(length)
                .toString();
    }
}
