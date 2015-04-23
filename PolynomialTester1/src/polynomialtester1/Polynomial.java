/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynomialtester1;

/**
 *
 * @author Sillychina
 */
public class Polynomial {

    double[] arguments, roots;
    Polynomial fdiv, sdiv;

    public Polynomial(double[] a) {
        arguments = a;
    }
    
    public Polynomial(String s) {
        this(Input.parse(s).arguments);
    }

    public Polynomial addPolynomial(Polynomial p) {
        
        int t = this.arguments.length;
        int f = p.arguments.length;
        int min = Math.min(t,f);
        int max = Math.max(t,f);
        
        double[] ans = new double[max];
        for (int i = 0; i < min; i++) {
            ans[i] = this.arguments[i] + p.arguments[i];
        }
        if (t>f) {
            for (int i = f; i < t; i++) {
                ans[i] = this.arguments[i];
            }
        }
        else{
            for (int i = t; i < f; i++) {
                ans[i] = p.arguments[i];
            }
        }
        return (new Polynomial(ans));
    }
    
    public Polynomial subPolynomial(Polynomial p) {
        
        int t = this.arguments.length;
        int f = p.arguments.length;
        int min = Math.min(t,f);
        int max = Math.max(t,f);
        
        double[] ans = new double[max];
        for (int i = 0; i < min; i++) {
            ans[i] = this.arguments[i] - p.arguments[i];
        }
        if (t>f) {
            for (int i = f; i < t; i++) {
                ans[i] = this.arguments[i];
            }
        }
        else{
            for (int i = t; i < f; i++) {
                ans[i] = -p.arguments[i];
            }
        }
        return (new Polynomial(ans));
    }
    
    public Polynomial multPolynomial(Polynomial p){
        
        int t = this.arguments.length;
        int f = p.arguments.length;
        
        double[] ans = new double[t+f-1];
        
        for (int i = 0; i < (t+f-1); i++) {
            ans[i] = 0;
        }
        
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < f; j++) {
                ans[i+j] = ans[i+j] + (this.arguments[i] * p.arguments[j]);
            }
        }
        return (new Polynomial(ans));
    }
    

    public Polynomial[] divPolynomial (Polynomial p){
        Polynomial[] ans = new Polynomial[2];
        int t = this.arguments.length;
        int f = p.arguments.length;
        int counter = f - t;
        double[] remainder = p.arguments.clone();
        double[] quotient = new double[counter + 1];
        double lead = this.arguments[t-1];
        for (int i = 0; i<=counter; i++) {
            quotient[counter-i] = remainder[(f-1)-i]/lead;
            for (int j = 0; j < t; j++) {
                double end = this.arguments[(t-1)-j] * quotient[counter-i];
                remainder[(f-1)-i-j] = remainder[(f-1)-i-j] - end;
            }
        }
        ans[0] = new Polynomial(quotient);
        ans[1] = new Polynomial(remainder);
        return ans;
    }
    
    public Polynomial derivative() {
        double[] result = new double[arguments.length - 1];
        for (int i = 0; i < result.length; i++) {
            result[i] = arguments[i+1] * (i + 1);
        }
        
        return new Polynomial(result);
    }
}
