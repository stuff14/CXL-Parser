public class CXLHeader {

    private static int packetNum;
    private int systemHeader;
    private int valid;
    private int memOpcode;
    private int snpType;
    private int metaField;
    private int metaValue;
    private int tag;
    private long addr;
    private int ldId;
    private int rsvd;
    private int tc;
    private byte[] immData;

    public CXLHeader(byte[] immData) {
        packetNum++;
        this.immData = immData;

        systemHeader = immData[0] | immData[1] << 8 | immData[2] << 16 | immData[3] << 24;
        valid = immData[4] & 0x01;
        memOpcode = (immData[4] & 0x1E) >> 1;
        snpType = (immData[4] & 0xE0) >> 5;
        metaField = immData[5] & 0x03;
        metaValue = (immData[5] & 0x0C) >> 2;
        tag = (immData[5] & 0xF0) << 8 | (immData[6] & 0xFF) << 4 | (immData[7] & 0x0F);
        addr = (long) (immData[7] & 0xF0) << 38 | (long) (immData[8] & 0xFF) << 34 | (long) (immData[9] & 0xFF) << 26 | (immData[10] & 0xFF) << 18 | (immData[11] & 0xFF) << 10 | (immData[12] & 0xFF) << 2 | (immData[12] & 0x03);
        ldId = (immData[13] & 0x3C) >> 2;
        rsvd = (immData[13] & 0xC0) << 12 | (immData[14] & 0xFF) << 10 | (immData[15] & 0xFF) << 2 | (immData[16] & 0x03);
        tc = (immData[16] & 0x0C) >> 2;

    }

    //Getters
    public int getSystemHeader() {
        return systemHeader;
    }

    public int getValid() {
        return valid;
    }

    public int getMemOpcode() {
        return memOpcode;
    }

    public int getSnpType() {return snpType;}

    public int getMetaField() {
        return metaField;
    }

    public int getMetaValue() {
        return metaValue;
    }

    public int getTag() {
        return tag;
    }

    public long getAddr() {
        return addr;
    }

    public int getLdId() {
        return ldId;
    }

    public int getRsvd() {
        return rsvd;
    }

    public int getTc() {
        return tc;
    }

    public String[] getArr() {
        return new String[]{Integer.toString(packetNum), Integer.toHexString(systemHeader), Integer.toHexString(valid), Integer.toHexString(memOpcode),
                Integer.toHexString(snpType), Integer.toHexString(metaField), Integer.toHexString(metaValue),
                Integer.toHexString(tag), Long.toHexString(addr), Integer.toHexString(ldId), Integer.toHexString(rsvd), Integer.toHexString(tc)};
    }

}
