package com.cueify.output.graph.finite;

import com.cueify.output.graph.Graph;
import com.cueify.output.graph.Range;

public interface FiniteDomainGraph extends Graph {
	public Range getDomain();
}
