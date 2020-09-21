	.file	"mcd.c"
	.text
	.globl	mcd
	.type	mcd, @function
mcd:
.LFB0:
	.cfi_startproc
	endbr64
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	movl	%edi, -4(%rbp)
	movl	%esi, -8(%rbp)
	jmp	.L2
.L5:
	movl	-4(%rbp), %eax
	cmpl	-8(%rbp), %eax
	jle	.L3
	movl	-4(%rbp), %eax
	cltd
	idivl	-8(%rbp)
	movl	%edx, -4(%rbp)
	jmp	.L2
.L3:
	movl	-8(%rbp), %eax
	cltd
	idivl	-4(%rbp)
	movl	%edx, -8(%rbp)
.L2:
	movl	-4(%rbp), %eax
	cmpl	-8(%rbp), %eax
	je	.L4
	cmpl	$0, -4(%rbp)
	je	.L4
	cmpl	$0, -8(%rbp)
	jne	.L5
.L4:
	cmpl	$0, -4(%rbp)
	je	.L6
	movl	-4(%rbp), %eax
	jmp	.L7
.L6:
	cmpl	$0, -8(%rbp)
	je	.L8
	movl	-8(%rbp), %eax
	jmp	.L7
.L8:
	movl	$1, %eax
.L7:
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	mcd, .-mcd
	.ident	"GCC: (Ubuntu 9.3.0-10ubuntu2) 9.3.0"
	.section	.note.GNU-stack,"",@progbits
	.section	.note.gnu.property,"a"
	.align 8
	.long	 1f - 0f
	.long	 4f - 1f
	.long	 5
0:
	.string	 "GNU"
1:
	.align 8
	.long	 0xc0000002
	.long	 3f - 2f
2:
	.long	 0x3
3:
	.align 8
4:
