(ns freeq.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [freeq.core-test]
   [freeq.common-test]))

(enable-console-print!)

(doo-tests 'freeq.core-test
           'freeq.common-test)
