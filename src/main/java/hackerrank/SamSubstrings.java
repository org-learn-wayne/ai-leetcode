package hackerrank;

public final class SamSubstrings {
    static int MOD = 1000*1000*1000+7;
    public static int substrings(String s) {
        System.out.printf("INPUT: %s\n", s);
        var total = 0L;
        var p10 = 1L;
        for(int si=s.length()-1; si>=0; si--) {
            var digit = s.charAt(si) - '0';
            var n = si+1;
            var curr = (n * digit * p10) % MOD;
            total = (total + curr) % MOD;
            System.out.printf("i %d, d %d, p10 %d => curr %d => SUM %d\n", si+1, digit, p10, curr, total);
            
            p10 = (p10*10 + 1) % MOD;
        }
        return (int)total;
    }
}