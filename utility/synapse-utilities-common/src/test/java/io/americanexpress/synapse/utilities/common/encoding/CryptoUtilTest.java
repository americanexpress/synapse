/*
 * Copyright 2020 American Express Travel Related Services Company, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.americanexpress.synapse.utilities.common.encoding;

import io.americanexpress.synapse.utilities.common.cryptography.CryptoUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * {@code CryptoUtilTest} tests the {@link CryptoUtil}.
 */
class CryptoUtilTest {

    private static final String ENCRYPTED_VALUE = "0fvMPUD069EJ7pJ4rmkdMtqc+83aES_jmGYKX57jL4e2XXeX4DBmA68qypDCzL08WMunmo0JzR6ey+RIRyaAUDH5YgjisXexxnDW0nMDb48fwi0+b69YvrF+ZIkDLrQYorlUefGxIOR01mtmTiKfQEdaaJ4w4CtVtKdAsd9QAC7_zWlAtQ_Ubsz++12PS8iDGlyK35T54j8nCHwcwZQd8tq9uvV8nocVUQBxgRVp5keJGA5e5Ujcb830l9NdEC2QanoVAde+PVgZMMpQcbJMgbwn139qfRN5+YDKNlsBONaejXOVfpMU18Ztr16+HZ4Jd5bTSk4UrZtRSAv8CZ9eBGBg6VURi3513YgFoVtIjuQVz4X7waIfBlwvjg_hmmWwQbQXidCQPQyre8mvTlt5iXcm7e0ZxxMvsXYYSkUSFe00BzV4fmHsmK9TwCOEjAgsO11KVnjnynyQCh551qskDZFfIO47o5n1gfLW3uPmUwnGiqMkUtr9WSBdVZ_dSmzs90+cLY7zahgOF9At0z6t4QwIFvPOmc39gkwOU+mStg66wwPnK0psSU79rNGTtxHWqYAG3jBoTUJLnCXbUM+bYrmPr0wECBV3yiAVHJnMrCOoSF1rIIlt5tEC3uL3eA+XXxRtz8Ia9JvTGlcOXZQWkdEC+HR+IG2HaR4nxpFu2tWmm5oa4lEMivoz5FE0aKfge9fvN9D7p1hENhDKcBwu8ByBgLvIAH7ZOeOQuJD+aujAiABoE3KAHob0UQ3Q5RgtDe7HvXn5WqDCLz6fupddiuNW10zA9E3jmOmjVdTdHwk5lDMXWlC79hTgHpk50vp2laa+pQCtADclS7W8shnKpEO4KE0egq1FxxbEMvGDpB2IsTlGtzTmELuI6cc6STOHM6BL0uMkmUJGJwK8AAsxPKVrZfKZqGh9i6cLRGEQks2iG_TFjexz6D5wdkKyaF6kIbkn5qRLViZaX1bZooFM6PA_t407XuLVGMFPfK3LecIL9gV9Sw9cyH9oCJ77OD2HSjSLYBhJTVKrt2ikdu63F6Se+nJ+Xi2dAKTtOR478xAiVwvnzIxbNJYWggJMGD21ryt5b1_o7UBNoqT0+0048zsTzPEBHslJbcaOY97C97PUgVyWo_F1sztqkpEuv6KXPptwWxc819L6pmbqyL0qXMhyMuBPJRdqUlCloVzDq_x+szi7gRrbLKBCHT5hy9qKKYNJXa0syShV6LVUNGx04B4L_TzKUDehJ7pMHRkYiLCzBlOJWBsu7J63MGWNzZlKhmXRXYqD_X8sD3rqCtdKM6xfmpr3HkMS8C9kNMTfo_Po8wEQOTNI780B4bcvE3qheslUfG0bbBiZmUpvImodYa8DgcCgYzKjKGVScRDj0pcEDDa5ol1nc3zPdQMNdCMchAdUfWQdTaEJmKXtC1XSjPhyvyboW1em_6qM4KN_kK+EycOmRjQTf9NGF5FqGp619Q7rhcWBB+Jwz8yYQa1MpRTLe74S_n67tneSzP27JL94HWQ2IBn_VAypW_dJpSVW5kzNA7LMNi4P3iTl4txzEBWVK37CNnafc3Dh7S+FrmmFDyRrg2qrKkQU7uPk7CUI81fjZksQyp4dx1QDZd15QvfNdRUq_yEYGEbKoiNCryRq_uSBkk1KoROqC1IJ_rB42SQaAjz2reNDvUNyRnZaa5NyZbUT3YH93qyJQycF6tWZZU5RI3MbTM4VBBU45RXGuET+nvfIdKk0cJT1oKLfjGZ9NFB2M+QuH8ZWLdm0LVdd7+0SiIk+qpTfGTpmTyVuwM75Tuibm2uvW9a51JVewHtmxP1eyuzOle3AlvOrXKC+txxUoHlbZi+wzaanvr0ahgXJP+UVonhNx7uH_fkT+mhX8rSVs0gs2cltFeThIQgnhXohOWs89UYzJER9iYU5DeLa3DuEY59ufp887ZxOGyZ_D2XAgycS7quEVxh9kLvYcxck5h0t1Y9_f1kzpyxNQTLH9mvtPR8U5_vB5fKpALBTVwgWaDhaTpGx8zajOSnn3ekBI590VgegS0tDkGcAkAvFLqEgPcgdNLHbu1w+Cc+9n7EN8+FyXIuc+vdrZ17cclgYsMw1Aer_PIciaIKNVV1wSDKBTrI5dL1N308s25K6FNemnCUJeGp3JtDWZkPFsk3+GdTwUIKxmRS4GrpeysJYORyN+2n6F55VKHphOiSXkpxbG7L6FlS5cwlviz4_4gl5KCD5J_yGfOVu6+oRiQoCHuM6qjmYHqiDNJqfI6jH0fPnqTOI5+vRXPUg3rVLaESXaXRBnNluNHRG7S0cMHqB5EUUib+M5Z28QgDHZBezC5qkjYgkNnpkl6JNCA6nLUONHsH+bPb+kJ+gnFEXF1mhH+Eja8n72kL6pB0LQnw7sTWabB9jqHk2rM22w1GAU_uoi7ukG24H26kWbMDIXJqCCHIn5huXxfTrkVK7uysMjXTgwHFNhhB85o6NgobQLm3lCeM68FR9dBIF70YPp9reY4Zak1F3tG4dk+cK+TdUSdVV_4m+mYimscHgWbzeJA3ZIQyP8_ps4ik8Og2ND7yK41WHUwuwFEay6f5w19z2j7ANckQm_Es_tZ7Wn0fZLmw_CtVDQm5DzFghqMApH4KvuoyKpJkAzjNA8bJaSSaEY_Xl+ntjVCXUU2zQbAQngzSoX+lETH_ZpSCm2zZluprFhQKTA0lzf+kYg_N+AlVNpJCElMNgj4rX4sMRzrieC8NUqHng2xqW5qS2_QhY31L7HJebVGVAt7uiRi+161BZ+HBvYn2coBVEbkz32mcyRWtASbf79OhfPDz3jlSb6QWPb9zfcoutIeYlrrfdyTOK8Qk27_byOtut1CGDGgYCqoNIkTkALS2gZdeLgj8cTjjRC9PDJuHHOb78O3pyT71ir8W5MyfY_Ne+ZiEIxYDYLcwPKRVkA1xfTC26wmeLA7ofb33hMF5LJIPpkJOqE99DpMpGJOvuhw7m9UfM+dRd5xWNUfXgr5yCsKS3512DkxZPpVGgL94j6yyVybbY2N04vIzGaODu7TqF0ciVjIKt_XstrwlKNG7ImDAhLA8YRwADbEwKRW1gMkkjss5Q1l6m9jUrOekVut+fHDWvEXx5vRwdziYZZS48YmyTq9+LYnITF9LDS_XxUgwpbKu6Zrnn7TqnsBZVEIoBRFOYCoodP9dE+m+UWbqNm8dh76bqWHUA3+mfgwnetcgrWrLoLyOziMFBSRVyaAroHaO87CwupCRAgKG0IlQbdc33nTjQqm52ZO80pq4KRE5FXJ3ID5GflPtRHolEXVvFJs1K_I2c0SrWlUVcaD0E1xG8bHIbK+0pjbpo1bcP8hCQq2IlfeKBPrl8JvzthLHnijiBZvzRno9nne3GSqYubzF0c8k0MOw+R9U1iMVBwXAP3a4EDS4MIRmlQ609_coo7ZXn9tTraVijl4Ge+Nf93dE6caxGYq7lFMqrWpSLV8LBNOtBHPbvjgKyQuyDZ8KtwLm4+TxXQZF2orUlPMvpM3FgSCpR88eaKEm74Z3EOx5Q4f8G_OM_w0x+VDcR9e33Yqgh3z46Od3ImvELILSNrHMnHRxPjk3DocR9qStTSevXO0rXDsQPB0wqlDcasOT8rSZqeOULflAjQl6eoGE5x2wvP27xEtSopsOY4RezmYtO+titfy2kZQi4tPcRIELR0kwdT6QLuqQcXetaPu07Jsgnh4b3ruxUHeuHOlEKLPuxbkfxCPi5AQzfMUFevTLYNi8hVPgGTUuNNic09f4iJrEQRVmHAFWVfgzNxXhod+iyUz+yi6Xoo+hK9E6GD5N1Ayayt+bdkJ5eLWRvWVjUjMQdPhZo8Ch2_AATOzXq3uwmtskmeVou+NbJpUOwjG_zakwc_7jxB4ckquqPprNjUcsowr2vFH9BAeYrDMarIPAxgUIs5GDZs+JqaYmennRur+n_G8DSF3LN+ftVk+W2m1PLthqQAXsBBX0iKBQYfV4mB+djeFkWdyAZ_GXyXg_r+fekbfiBOqBoRsoUFtbI+jEN58A66hnaTjmg8RH3ivNF3AvGtD7798YmzQZyenONEDaioede0qSF2kdxmSboFksI6DXFGsyxarfZ6a_EDvwUREWtbLQW66Fsb4cNJCrkyYRL6rqHax2hPTi_P31YEq9EORwhM69Thn_RIo+12WBLlwPXBiIbWmQe2VgDYQLEQqACGneu3o10QZs6e_A_sM4vk+sz0b+8ET8Gv9m1qgGI6s7n2RkUJV83WKvT1mZEdyc7mpOhkT7kqy3vNwZQQTCAxSa+NLnX9hk7JMcPvA_LkyTLi0ESgic1x_iX_n5nqPC_2In2kdBt5RlfOzo0v_rI3jKSxhU_ZNcygUZZvjXM9sV_cinHrkmUHa43T06LwR7lQrtrcALkS3qFEXsjUsMAX3lkRvmGlGCIRAmsuepH8EIATt9tBvKnDh_twDmmXWsk_JMSLCOV0HpsZ3fQLWGpXOPCg6j_YRJ+ANtNbC+EJT55bnXXoyViT6X50fi8E9Esg4+0E+UVRQUfuCdo3b0GTRk_vS5StXEoZAyfhbsNNaOkyTcDpwGbn3pYWEn_NFodSM1TOOJfpvskcZo96kQlMlTBayiHPm+zEL61dbOoTvmGKYRcnZm1HJ6Elqz2NjMw0tXn5L7XAvFa9Vrbnm04HCTfX92mZgZbHYcoqvqO8VrW0dDmtgfFrKpVGc0O_Ybx2sq_gXh+Vzq2JFevg2hRkKh_oaM0145Z49TQURStDPDEQZgS9G+TBeROw0rmvjvGukqrou8oAHOFCHO9adxDon8JEQuPkCTSl0M8qGSlZP9YCBkqHtv8Dlf0y3kh8XSBy97nmDGboF4mPA1lGxm8i+N17I2uk0j0Hu3QzZB0CX_3qmLh9jK6ujv96a123xK3Vhm9HeviLrENv8kCPGyy4vPCZWFysJM1FR3IIX0lcHSqZzj9m9+CYkucdfyHGzduPgBZLO++EU80_oYiByAGeF3XrjTvBUmD63j91fYgkRQ9P8bYm93ihUsyI_qd_cKROCsFoz7bOCMvVnzp8vIzHmF2T1nZvBGQQCZ0E5xlRNEsNEc4Vy9F3frBFRKweI4SX61g1lasqwfTmuope4tF_awO09vkPmec7qWs5jwz_RNITkesFO4LJepqIWkwl_rsYbMwqoaTnCgNdKRcT_YhTB5Vc2BRB6F8+c_MmeuGhxJLhExM9Zj_YYh3TeQ1r5lH8wFxZf2wuKHlzBpVj5D8q_4o4ke36vh2OfyfvUIR_Eyzjjf4M3p3kZnieqrXQFdsqM6G0tF5p1GQuEOVh1wlaTekiFlvvoiJhMJobcPOjvjeBGjh+5B7ndht3d8AI7D6ENR01S3zRMc+NSXLTIafYHagY4kacvkcJHNcGwEySNS+he3tz4gGXXjQoDHDsscW8V6b95+s3uYHGb6qTtK6Ug4kvS68Ijeu+IcP3e4muNRxEISjGlRajto7Cfd1fksCEM0D_IyL4WBw3KQbLYFg8hSxF_uDFjm7K6PRWdvYRCgAaaldWk0RP3NQlgq+5piKtPW97yS6VMcY37JPGGvO5O9gb5FatXYBK5hs3SKDF_uwaq4oXmlIDxd2DIi7alV4g7aW2hsY5d2SXc6kU7JgyLFkbt3JcUvn0JVF4_Wras1pRgxmOZ8bQOwCPCRlNA5LfutXe7goUzyerZRXy7HDdVG9uqV9cRNQf_uH7hiKAORkeWXtm4jRFkR4IiatVBCWX9HcZIAnVcmpxJs23RtdoywOZsz4dNSBUYm7w3Yvb48gPRt0q_U2TA95KbTc1BgNExHuEYAly_oE+n61AngvWX_pY2OTG6bxX+NwD66tfghctwH6_nsQfsE_hlQHmpxixqbMJyYn_RXjlpxGSzKtndZUzVx4c8h5rbKcpHFeyuavNY9z_dswcoj+3xfCYba_h6ZAWB4AcmTt9rY6zpWSnOBDHjN95P2sY0nfKyA19BtPGjbWHwDZ5H0Q+1xcfLUkk4icosphQCHfh7f4Sm3qC1IyUjbOkyfNqy6ZwatcX9yOPWPUuOjchGzUvw2JTjuxAyi2StOKwdEXgAwDFvD8jLly_nCVLE9KvpfdzrGMrBUq8MZLC98gABugQ+TIOu8nb6LJCBgViZBU35hZbwtLpg+lqJcNq3rrXHOhmwbPmQE4aY_EDhJHrFvlDclqkI1Sudo7ky7IoBD5aiDVccbeprL436R__BC0F6pFkHZGknQoNXPmm3zttapgJ9Ec1ha9GGtvgyjyCIRGcZnBo6Way6GCvBotcHeXW4hCLg9StHxspmAgkVVH5mWlyo9bxfokdAa+_f3gMWSYwG3mcC4Cagul+liyZq25P_dxUmYhBku6iAxaBWfGrVdulIWH+A2SRlVujuG3i9yWQCz1AAOOdhEta6lFQk5x6jcKsnA8WE66GaQiB8C6vCxAqjtlGoQQpKdqRDBDhoM5HRV3SuCgTdjChWANGUaIwxXx_zuGIRsuVLFBI4Z4YwoOC+tDhVQieCZ+FjqVD2GRIpavKPxOG6OuzLzeTu0gRCGzESRF4BjqjB2NPdXLfWjQT0KMK+n5gL73_nrK85PjKXceN6rUqfDn8NgFn_cLlDomy5SEzPnFvNqBBDJOAf_UGwPsjf1x+D+IrjJ0ND1AxGyZjC9uIXaWfTzV+MblRK3hW1vPmNxjw6UIodACF0zHS1JSCkdaLYwgHPRjt__jHS9bSEIH55fGkh6EEw1MnwKILadOonnWDhfIyahj8S0mSKr8zUJv8WM=";

    private static final String DECRYPTED_VALUE = "184981684091";

    @Test
    void tryJasyptDecrypt_givenEncryptedString_expectedDecryptedString() {
        String decrypted = CryptoUtil.tryDecrypt(ENCRYPTED_VALUE);
        assertEquals(DECRYPTED_VALUE, decrypted);
    }

    /**
     * Testing to make sure decryption will not return a string that has a number in it.
     */
    @Test
    void jasyptDecrypt_givenEncryptedString_expectedDecryptedString() {
        String decrypted = CryptoUtil.decrypt(ENCRYPTED_VALUE);
        assertEquals(DECRYPTED_VALUE, decrypted);
    }

    @Test
    void tryJasyptEncrypt_givenDecryptedString_expectedEncryptedString() {
        String decrypted = CryptoUtil.tryEncrypt(DECRYPTED_VALUE);
        assertEquals(ENCRYPTED_VALUE, decrypted);
    }

    @Test
    void jasyptEncrypt_givenDecryptedString_expectedEncryptedString() {
        String encrypt = CryptoUtil.encrypt(DECRYPTED_VALUE);
        assertEquals(ENCRYPTED_VALUE, encrypt);
    }
}
