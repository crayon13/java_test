import com.google.common.base.Joiner;
import org.apache.commons.lang.BooleanUtils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        String nowDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        boolean elevenDay =  nowDate.equals("20181110") ? true : false;
        System.out.println(nowDate + ":" + elevenDay);


        List<String> a = null;

//        System.out.println(a.isEmpty());

        for (CheckURI uri : CheckURI.values()) {
            try {
                System.out.println(uri.name() + ":" + uri.ordinal() + ":" + uri.redirectOrExceptURI() );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        String value = String.valueOf(System.currentTimeMillis()) ;
        System.out.println("value " + value);
        StringBuffer buf = new StringBuffer() ;
        buf.append(value);

        for ( int i=0 ; i<10 ; i++ ) {
            char random = String.valueOf(Math.random()).charAt(2) ;
            //log.debug("makeCookieIfNot random "+i+":"+random) ;
            buf.append(random) ;

            System.out.println("random " + random);

        }


        System.out.println(">>>" + buf.toString().length());


        String checkString = "i.011st.com";
        System.out.println("ii.011st.com".indexOf(checkString));
        System.out.println("i.011st.com".indexOf(checkString));

        if ( "ii.011st.com".indexOf(checkString) > -1 ) {

        } else if ( "ii.011st.com".contains("ii.011st.com")) {

        }


        List<String> tl = new ArrayList<>();
        tl.add("11");


        if ( ! tl.isEmpty() ) {
            System.out.println("not  empty");
        }

        if ( tl.contains("1") ) {
            System.out.println("y");
        } else {
            System.out.println("n");
        }


        if ( tl.indexOf("1") > -1 ) {
            System.out.println("y");
        } else {
            System.out.println("n");
        }


        File srcFile = new File("");
        File dstFile = new File("");


        List<String> itemList = new ArrayList<>();
        itemList.add("1");
        itemList.add("2");

        System.out.println(Joiner.on("&itemList=").join(itemList));


        System.out.println(BooleanUtils.toBoolean("true"));


    }



    private enum CheckURI {
        A(true) {
            @Override
            public boolean redirectOrExceptURI() throws IOException {
                return getReturnValue();
            }
        },
        B(true) {
            @Override
            public boolean redirectOrExceptURI() throws IOException {
                return getReturnValue();
            }
        },
        C(false) {
            @Override
            public boolean redirectOrExceptURI() throws IOException {
                return getReturnValue();
            }
        },
        D(true) {
            @Override
            public boolean redirectOrExceptURI() throws IOException {
                return getReturnValue();
            }
        };


        private boolean returnValue;

        CheckURI(boolean returnValue) {
            this.returnValue = returnValue;
        }

        public boolean getReturnValue() {
            return this.returnValue;
        }

        abstract public boolean redirectOrExceptURI() throws IOException;
    }
}
