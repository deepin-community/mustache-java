package com.github.mustachejava.codegen;

import com.github.mustachejava.reflect.Guard;
import com.github.mustachejava.reflect.MissingWrapper;
import com.github.mustachejava.util.GuardException;

import java.util.List;

public class CodegenMissingWrapper extends MissingWrapper {
  final Guard compiledGuards;

  public CodegenMissingWrapper(String name, List<Guard> guards) {
    super(name, guards.toArray(new Guard[guards.size()]));
    compiledGuards = GuardCompiler.compile(guards);
  }

  @Override
  protected void guardCall(List<Object> scopes) throws GuardException {
    if (!compiledGuards.apply(scopes)) {
      throw guardException;
    }
  }
}
