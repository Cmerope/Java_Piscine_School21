package renderer;

import preprocessor.PreProcessor;

public class RendererErrImpl implements Renderer {
    private PreProcessor preProcessor;

    public RendererErrImpl (PreProcessor preProcessor){
        this.preProcessor = preProcessor;
    }
    @Override
    public void print(String text) {
        text = preProcessor.preProcess(text);
        System.err.println(text);
    }
}
