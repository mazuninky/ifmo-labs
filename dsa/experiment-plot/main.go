package main

import (
	"io/ioutil"
	"github.com/golang/protobuf/proto"
	"github.com/zerotwoone/experiment-plot/proto"
	"gonum.org/v1/plot"
	"gonum.org/v1/plot/plotter"
	"gonum.org/v1/plot/vg"
	"gonum.org/v1/plot/plotutil"
)

type Point struct {
	X float64
	Y float64
}

func main() {
	readExp, _ := ioutil.ReadFile("experiments3.bin")
	var exp experiment.Experiments
	proto.Unmarshal(readExp, &exp)

	p, err := plot.New()
	if err != nil {
		panic(err)
	}

	var markAMethodA plotter.XYs
	var markAMethodB plotter.XYs
	var markBMethodA plotter.XYs
	var markBMethodB plotter.XYs

	for _, item := range exp.Experiment {
		if item.Mark == 1 {
			if item.Method == 1 {
				markAMethodA = append(markAMethodA, Point{float64(item.N), float64(item.Time)})
			} else {
				markAMethodB = append(markAMethodB, Point{float64(item.N), float64(item.Time)})
			}
		} else {
			if item.Method == 1 {
				markBMethodA = append(markBMethodA, Point{float64(item.N), float64(item.Time)})
			} else {
				markBMethodB = append(markBMethodB, Point{float64(item.N), float64(item.Time)})
			}
		}
	}

	//plotutil.AddLinePoints(p, "MethodA", markAMethodA, "MethodB", markAMethodB)
	plotutil.AddLinePoints(p, "MethodA", markBMethodA, "MethodB", markBMethodB)

	if err := p.Save(8*vg.Inch, 16 * vg.Inch, "points.png"); err != nil {
		panic(err)
	}
}
