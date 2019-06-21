import org.junit.Assert;
import org.junit.Test;

import java.io.ObjectStreamClass;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;


public class EnumTest {

    @Test
    public void FileWriteModeTest() {
        int given = 0;

        Assert.assertTrue(FileWriteMode.DIRECT_FILE_WRITE.equals(0));
        Assert.assertTrue(FileWriteMode.DIRECT_FILE_WRITE.name().equals("DIRECT_FILE_WRITE"));
    }

    @Test
    public void RealGeneratorServerTest() {
        Assert.assertTrue(RealGeneratorServer.D1.getHostNames().contains("11DEPLOYb-statR01.br.skp"));
        Assert.assertFalse(RealGeneratorServer.D2.getHostNames().contains("11DEPLOYb-statR01.br.skp"));
        Assert.assertTrue(RealGeneratorServer.D2.getHostNames().contains("tmbatch1"));
    }


    @Test
    public void nameNotFoundTest() {
        RealGeneratorServer realGeneratorServer;

        try {
            realGeneratorServer = RealGeneratorServer.valueOf("aa");
        } catch (IllegalArgumentException e) {
            realGeneratorServer = RealGeneratorServer.D2;
        }

        Assert.assertThat(realGeneratorServer, is(RealGeneratorServer.D2));


    }

    @Test
    public void serverZoneTest() {
        Assert.assertThat(ServerZone.findByHostName("vfrontbatch01"), is(ServerZone.D2D3));
        Assert.assertThat(ServerZone.findByHostName("aa"), is(ServerZone.DEV));

        Assert.assertThat(ServerZone.findSlackChannelByHostName("vfrontbatch01")
                , is(ServerZone.D2D3.slackChannel));

    }

    private enum FileWriteMode {
        DIRECT_FILE_WRITE(0),
        TEMPORARY_FILE_RENAME(1),
        TEMPORARY_FILE_COPY(2);

        private final int modeNumber;

        FileWriteMode (int modeNumber) {
            this.modeNumber = modeNumber;
        }

		public boolean equals(int modeNumber) {
			return this.modeNumber == modeNumber;
		}
    }


    private enum RealGeneratorServer {
        D1("#log_htmlgen_alarm_D1") {
            @Override
            public Set<String> getHostNames() {
                Set<String> hostNames = new HashSet<>();

                hostNames.add("11DEPLOYb-statR01.br.skp");
                return hostNames;
            }
        },
        D2("#log_htmlgen_alarm") {
            @Override
            public Set<String> getHostNames() {
                Set<String> hostNames = new HashSet<>();

                hostNames.add("vfrontbatch01");
                hostNames.add("tmbatch1");
                return hostNames;
            }
        },
        DEV("#log_htmlgen_alarm_dev") {
            @Override
            public Set<String> getHostNames() {
                return null;
            }
        };

        private final String slackChannel;

        RealGeneratorServer(String slackChannel) {
            this.slackChannel = slackChannel;
        }

        abstract public Set<String> getHostNames();
    }

    @Test
    public void errorTest() {
        ServerZone serverZone;

        try {
            serverZone = ServerZone.valueOf("aaa");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        serverZone = ServerZone.get("aa");
        Assert.assertThat(Objects.isNull(serverZone), is(true));

        if ( Objects.isNull(serverZone) ) {

        }
    }



    enum ServerZone {
        D2D3(true, "#log_htmlgen_alarm") {
            @Override
            protected Set<String> getHostNames() {
                Set<String> hostNames = new HashSet<>();
                hostNames.add("vfrontbatch01");
                hostNames.add("tmbatch1");
                return hostNames;
            }
        },
        D1(false, "#log_htmlgen_alarm_d1") {
            @Override
            protected Set<String> getHostNames() {
                Set<String> hostNames = new HashSet<>();
                hostNames.add("11DEPLOYb-statR01.br.skp");
                return hostNames;
            }
        },
        DEV(false, "#log_htmlgen_alarm_dev") {
            @Override
            protected Set<String> getHostNames() {
                return new HashSet<>();
            }
        };

        private final boolean sendSms;
        private final String slackChannel;

        ServerZone(boolean sendSms, String slackChannel) {
            this.sendSms = sendSms;
            this.slackChannel = slackChannel;
        }

        abstract protected Set<String> getHostNames();

        public static ServerZone findByHostName(String hostName) {
            for ( ServerZone serverZone : ServerZone.values() ) {
                if ( serverZone.getHostNames().contains(hostName) ) {
                    return serverZone;
                }
            }
            return ServerZone.DEV;
        }

        public static String findSlackChannelByHostName(String hostName) {
            return findByHostName(hostName).slackChannel;
        }

        public static ServerZone get(String name) {
            try {
                return ServerZone.valueOf(name);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

}
