import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class PriceTest {
    @BeforeEach
    public void setUp() {

    }


    @Test
    public void getDiscountRateTest() {
        System.out.println(this.getDiscountRate(60900, 59900) );
    }

    /**
     * ���������� ��� <br>
     * - �Ҽ����� ���� ó�� (�ݿø� ����)
     * @param selPrc �ǸŰ�
     * @param finalDscPrc �������ΰ�
     * @return ������
     */
    public int getDiscountRate(long selPrc, long finalDscPrc) {
        int discountRate = 0;

        BigDecimal dSellPrice = new BigDecimal(Long.toString(selPrc));
        BigDecimal dFinalPrice = new BigDecimal(Long.toString(finalDscPrc));

        try {
            if (dSellPrice.compareTo(dFinalPrice) == 1) {
                BigDecimal dscRate = (dSellPrice.subtract(dFinalPrice)).divide(dSellPrice, 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal("100"));
                dscRate = dscRate.setScale(0); // �Ҽ��� ���� ����
                discountRate = dscRate.intValue();
            }
        } catch (Exception e){
            //e.printStackTrace();
        }

        return discountRate;

    }
}
