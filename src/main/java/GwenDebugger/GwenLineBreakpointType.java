package GwenDebugger;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.xdebugger.breakpoints.XLineBreakpointType;

public class GwenLineBreakpointType extends XLineBreakpointType<GwenBreakpointProperties> {
    public GwenLineBreakpointType(){
        super("GwenID", "GwenTitle");
    }
    @Override
    public GwenBreakpointProperties createBreakpointProperties(VirtualFile file, final int line){
        return null;
    }

    @Override
    public GwenBreakpointProperties createProperties(){
        return new GwenBreakpointProperties();
    }


}
