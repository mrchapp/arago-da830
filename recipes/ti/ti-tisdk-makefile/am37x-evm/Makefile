-include Rules.make

all: matrix_gui matrix_tui am_benchmarks


matrix_gui:
	@echo =============================
	@echo      Building Matrix GUI
	@echo =============================
	@cd demos/matrix_gui; make -f Makefile.build

matrix_tui:
	@echo =============================
	@echo      Building Matrix TUI
	@echo =============================
	@cd demos/matrix_tui; make all

am_benchmarks:
	@echo =============================
	@echo    Building AM Benchmarks
	@echo =============================
	@cd demos/am_benchmarks; make

matrix_gui_clean:
	@echo =============================
	@echo      Cleaning Matrix GUI
	@echo =============================
	@cd demos/matrix_gui; \
	 make -f Makefile.build clean; \
	 rm -f Makefile Makefile.Debug Makefile.Release

matrix_tui_clean:
	@echo =============================
	@echo      Cleaning Matrix TUI
	@echo =============================
	@cd demos/matrix_tui; make clean

am_benchmarks_clean:
	@echo =============================
	@echo    Cleaning AM Benchmarks
	@echo =============================
	@cd demos/am_benchmarks; make clean

clean: matrix_gui_clean matrix_tui_clean am_benchmarks_clean

matrix_gui_install:
	@echo =============================
	@echo     Installing Matrix GUI
	@echo =============================
	@cd demos/matrix_gui; make -f Makefile.build install;

matrix_tui_install:
	@echo =============================
	@echo     Installing Matrix TUI
	@echo =============================
	@cd demos/matrix_tui; make install

am_benchmarks_install:
	@echo =============================
	@echo   Installing AM Benchmarks
	@echo =============================
	@cd demos/am_benchmarks; make install

2D_accel_install:
	@echo =============================
	@echo   Installing 2D accel demo
	@echo =============================
	@cd demos/2D_accel; make install
install: matrix_gui_install matrix_tui_install am_benchmarks_install 2D_accel_install
