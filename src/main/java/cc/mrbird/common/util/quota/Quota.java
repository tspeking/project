package cc.mrbird.common.util.quota;

public enum Quota {
	_0001("0001", "营业收入"),
    _0002("0002", "毛利率"),
    _0003("0003", "净利润率"),
    _0004("0004", "经营性净利润率"),
    _0005("0005", "经营性净利润增长率"),
    _0006("0006", "净资产收益率"),
    _0007("0007", "经营现金流量净额"),
    _0008("0008", "投资现金流量净额"),
    _0009("0009", "筹资现金流量净额"),
    _0010("0010", "净现比"),
    _0011("0011", "应收账款"),
    _0012("0012", "应收票据"),
    _0013("0013", "预收账款"),
    _0014("0014", "流动比率"),
    _0015("0015", "速动比率"),
    _0016("0016", "现金比率"),
    _0017("0017", "利息收入倍数"),
    _0018("0018", "资产负债率"),
    _0019("0019", "营业收入增长率");
	
	public final String value;
    public final String name;

    Quota(String value, String name) {
        this.value = value;
        this.name = name;
    }

	@Override
	public String toString() {
        return this.value;
    }
	
	public static String getQuotaName(String value) {
        if (value == null || "".equals(value.trim())) {
            return "";
        }
        try {
        	Quota intTxnCd = get(value);
            return intTxnCd.name;
        } catch (Exception e) {
            return "未知类型";
        }
    }
	
	public static Quota get(String str) {
		Quota[] cs = Quota.class.getEnumConstants();
        for (Quota c : cs) {
            if (c.toString().equals(str)) {
                return c;
            }
        }
        throw new RuntimeException(" wrong " + Quota.class.getSimpleName() + " string value! " + str);
    }
}
