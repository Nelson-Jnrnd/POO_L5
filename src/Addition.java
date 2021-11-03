/**
 * Authors:     Alen Bijelic, Nelson Jeanrenaud
 * Date:        03.11.2021
 *
 * Description: Allows to compute addition between two integers
 *
 * Comments:    None
 */
public class Addition implements Operation{
    @Override
    public int compute(int a, int b) {
        return a + b;
    }
}
