(ns ^:figwheel-always fig.core
  (:require [re-com.core :refer [h-box v-box box gap line input-text input-textarea label checkbox radio-button slider title p]]
            [re-com.misc :refer [input-text-args-desc]]
    ;[re-demo.utils :refer [panel-title title2 args-table github-hyperlink status-text]]
            [reagent.core :as reagent]))

(enable-console-print!)

(defonce app-state (reagent/atom {:text "Hello world!"}))

(defn on-js-reload [])

(defn my-on-change-event [model data]
  (println (str "Data: " data))
  (reset! model data))

(defn main-component []
  (let [text-val (reagent/atom "Data in the model")
        regex (reagent/atom nil)
        regex999 #"^(\d{0,2})$|^(\d{0,2}\.\d{0,1})$"
        status :error
        status-icon? true
        status-tooltip "This is a tooltip"
        disabled? false
        change-on-blur? true]
    (fn
      []
      [v-box
       :size "auto"
       :gap "10px"
       :children [[h-box
                   :gap "100px"
                   :children [
                              [v-box
                               :gap "10px"
                               :children [
                                          [h-box
                                           :gap "40px"
                                           :children [[v-box
                                                       :children [[label :label "[input-text ... ]"]
                                                                  [gap :size "5px"]
                                                                  [input-text
                                                                   :model text-val
                                                                   :status status
                                                                   :status-icon? status-icon?
                                                                   :status-tooltip status-tooltip
                                                                   :width "300px"
                                                                   :placeholder (if @regex "enter number (99.9)" "placeholder message")
                                                                   :on-change #(my-on-change-event text-val %)
                                                                   :validation-regex @regex
                                                                   :change-on-blur? change-on-blur?
                                                                   :disabled? disabled?]
                                                                  [gap :size "20px"]
                                                                  [label :label "label 1 "]
                                                                  [gap :size "20px"]
                                                                  [label :label "label 2 "]
                                                                  [gap :size "20px"]
                                                                  [label :label "label 3 "]
                                                                  [gap :size "20px"]
                                                                  [label :label "label 4 "]
                                                                  [gap :size "20px"]
                                                                  [label :label "label 5 "]]]
                                                      ]]]]]]]])))

(defn ^:export run []
  (reagent/render [main-component] (js/document.getElementById "app")))

(run)
