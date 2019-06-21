import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class PriceTest {
    @Before
    public void setUp() {

    }


    @Test
    public void getDiscountRateTest() {
        System.out.println(this.getDiscountRate(60900, 59900) );
    }

    /**
     * 가격할인율 계산 <br>
     * - 소수점은 버림 처리 (반올리 안함)
     * @param selPrc 판매가
     * @param finalDscPrc 최종할인가
     * @return 할인율
     */
    public int getDiscountRate(long selPrc, long finalDscPrc) {
        int discountRate = 0;

        BigDecimal dSellPrice = new BigDecimal(Long.toString(selPrc));
        BigDecimal dFinalPrice = new BigDecimal(Long.toString(finalDscPrc));

        try {
            if (dSellPrice.compareTo(dFinalPrice) == 1) {
                BigDecimal dscRate = (dSellPrice.subtract(dFinalPrice)).divide(dSellPrice, 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal("100"));
                dscRate = dscRate.setScale(0); // 소숫점 없는 포멧
                discountRate = dscRate.intValue();
            }
        } catch (Exception e){
            //e.printStackTrace();
        }

        return discountRate;

    }
}
