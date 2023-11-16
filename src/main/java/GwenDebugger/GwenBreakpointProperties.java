package GwenDebugger;

import com.intellij.xdebugger.breakpoints.XBreakpointProperties;

public class GwenBreakpointProperties extends XBreakpointProperties<GwenBreakpointProperties> {
    public String option;

    @Override
    public GwenBreakpointProperties getState() {
        return this;
    }

    @Override
    public void loadState(final GwenBreakpointProperties state){
        option = state.option;
    }
}
