package search;

public enum Korean {
    CHOSUNG(
        new String[] {
            "ㄱ", "ㄲ", "ㄴ", "ㄷ", "ㄸ", "ㄹ", "ㅁ", "ㅂ", "ㅃ",
            "ㅅ", "ㅆ", "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
        },
        new String[] {
            "r", "R", "s", "e", "E", "f", "a", "q", "Q",
            "t", "T", "d", "w", "W", "c", "z", "x", "v", "g"
        },
        new String[] {
            "k", "kk", "n", "d", "tt", "l", "m", "b", "pp",
            "s", "ss", "", "j", "jj", "ch", "k", "t", "p", "h"
        },
        new char[] {
            0x3131, 0x3132, 0x3134, 0x3137, 0x3138, 0x3139, 0x3141, 0x3142, 0x3143,
            0x3145, 0x3146, 0x3147, 0x3148, 0x3149, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e
        }
    ) {
        @Override
        public int getCharIndex(char chars) {
            return chars / (21 * 28);
        }
    },
    JUNGSUNG(
        new String[] {
            "ㅏ", "ㅐ", "ㅑ", "ㅒ", "ㅓ", "ㅔ", "ㅕ", "ㅖ",
            "ㅗ", "ㅘ", "ㅙ", "ㅚ", "ㅛ", "ㅜ", "ㅝ", "ㅞ", "ㅟ", "ㅠ",
            "ㅡ", "ㅢ", "ㅣ"
        },
        new String[] {
            "k", "o", "i", "O", "j", "p", "u", "P",
            "h", "hk", "ho", "hl", "y", "n", "nj", "np", "nl", "b",
            "m", "ml", "l"
        },
        new String[] {
            "a", "ae", "ya", "yae", "eo", "e", "yeo", "ye",
            "o", "wa", "wae", "oe", "yo", "u", "wo", "we", "wi", "yu",
            "eu", "ui", "i"
        },
        new char[] {
            0x314f, 0x3150, 0x3151, 0x3152, 0x3153, 0x3154, 0x3155, 0x3156,
            0x3157, 0x3158, 0x3159, 0x315a, 0x315b, 0x315c, 0x315d, 0x315e, 0x315f, 0x3160,
            0x3161, 0x3162, 0x3163
        }
    ) {
        @Override
        public int getCharIndex(char chars) {
            return chars % (21 * 28) / 28;
        }
    },
    JONGSUNG(
        new String[] {
            "", "ㄱ", "ㄲ", "ㄳ",
            "ㄴ", "ㄵ", "ㄶ", "ㄷ",
            "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ",
            "ㅁ", "ㅂ", "ㅄ", "ㅅ", "ㅆ", "ㅇ",
            "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
        },
        new String[] {
            "", "r", "R", "rt",
            "s", "sw", "sg", "e",
            "f", "fr", "fa", "fq", "ft", "fx", "fv", "fg",
            "a", "q", "qt", "t", "T", "d",
            "w", "c", "z", "x", "v", "g"
        },
        new String[] {
            "", "g", "kk", "k",
            "n", "n", "n", "t",
            "l", "l", "l", "l", "l", "l", "l", "l",
            "m", "p", "p", "s", "ss", "ng",
            "j", "ch", "k", "t", "p", "h"
        },
        new char[] {
            0x0000, 0x3131, 0x3132, 0x3133,
            0x3134, 0x3135, 0x3136, 0x3137,
            0x3139, 0x313a, 0x313b, 0x313c, 0x313d, 0x313e, 0x313f, 0x3140,
            0x3141, 0x3142, 0x3144, 0x3145, 0x3146, 0x3147,
            0x3148, 0x314a, 0x314b, 0x314c, 0x314d, 0x314e
        }
    ) {
        @Override
        public int getCharIndex(char chars) {
            return chars % (21 * 28) % 28;
        }

        @Override
        public String convertEnglishKeyboard(char chars) {
            if ( getCharIndex(chars) != 0x0000 ) {
               return super.convertEnglishKeyboard(chars);
            }

            return "";
        }

        @Override
        public String convertEnglishSound(char chars) {
            if ( getCharIndex(chars) != 0x0000 ) {
                return super.convertEnglishSound(chars);
            }

            return "";
        }
    },
    SINGLE_JAUM(
        new String[] {
            "ㄱ", "ㄲ", "ㄳ",
            "ㄴ", "ㄵ", "ㄶ", "ㄷ", "ㄸ",
            "ㄹ", "ㄺ", "ㄻ", "ㄼ", "ㄽ", "ㄾ", "ㄿ", "ㅀ",
            "ㅁ", "ㅂ","ㅃ", "ㅄ", "ㅅ", "ㅆ",
            "ㅇ", "ㅈ", "ㅉ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"
        },
        new String[] {
            "r", "R", "rt",
            "s", "sw", "sg", "e", "E",
            "f", "fr", "fa", "fq", "ft", "fx", "fv", "fg",
            "a", "q","Q", "qt", "t",
            "T", "d", "w", "W", "c", "z", "x", "v", "g"
        },
        new String[] {
            "g", "kk", "k",
            "n", "n", "n", "t", "t",
            "l", "l", "l", "l", "l", "l", "l", "l",
            "m", "p", "p", "p", "s", "ss",
            "ng", "j", "jj", "ch", "k", "t", "p", "h"
        },
        null
    ) {
        @Override
        public int getCharIndex(char chars) {
            return (chars - 34097);
        }
    },
    SINGLE_MOUM(null, null, null, null) {
        @Override
        public int getCharIndex(char chars) {
            return (chars - 34127);
        }

        @Override
        public String convertEnglishKeyboard(char chars) {
            return JUNGSUNG.englishKeyboards[getCharIndex(chars)];
        }

        @Override
        public String convertEnglishSound(char chars) {
            return JUNGSUNG.englishSounds[getCharIndex(chars)];
        }
    };

    private String[] koreaKeyborads;
    private String[] englishKeyboards;
    private String[] englishSounds;
    private char[] unicodes;

    Korean(String[] koreaKeyborads, String[] englishKeyboards, String[] englishSounds, char[] unicodes) {
        this.koreaKeyborads = koreaKeyborads;
        this.englishKeyboards = englishKeyboards;
        this.englishSounds = englishSounds;
        this.unicodes = unicodes;
    }

    public abstract int getCharIndex(char chars);

    public String convertEnglishKeyboard(char chars) {
        return englishKeyboards[getCharIndex(chars)];
    }

    public char convertAtomic(char chars) {
        return unicodes[getCharIndex(chars)];
    }

    public String convertEnglishSound(char chars) {
        return englishSounds[getCharIndex(chars)];
    }
}
